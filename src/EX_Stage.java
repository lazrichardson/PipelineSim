public class EX_Stage {

    // EX READ
    ControlSignal exRead_ControlSignal; // control signal
    int exRead_ProgramCounter; // EX start
    int exRead_ReadData1; // EX start
    int exRead_ReadData2; // EX start
    int exRead_SignExtendedOffset;// EX start
    int exRead_WriteRegister_20_16; // EX start
    int exRead_WriteRegister_15_11; // EX start
    int exRead_RFormatFunc;
    int exRead_AluOp;
    int exRead_AluControl;

    // EX WRITE
    ControlSignal exWrite_ControlSignal; // control signal
    int exWrite_CalcBTA; // DON'T NEED TO IMPLEMENT
    int exWrite_AluZero; // EX end
    int exWrite_AluResult; // EX end
    int exWrite_SwValue; // EX end
    int exWrite_WriteRegNum; // EX end

    public ControlSignal getExWrite_ControlSignal() {
        return exWrite_ControlSignal;
    }

    public int getExWrite_CalcBTA() {
        return exWrite_CalcBTA;
    }

    public int getExWrite_AluZero() {
        return exWrite_AluZero;
    }

    public int getExWrite_AluResult() {
        return exWrite_AluResult;
    }

    public int getExWrite_SwValue() {
        return exWrite_SwValue;
    }

    public int getExWrite_WriteRegNum() {
        return exWrite_WriteRegNum;
    }

    public EX_Stage(ControlSignal idWrite_ControlSignal, int idWrite_ProgramCounter, int idWrite_ReadData1, int idWrite_ReadData2,
                    int idWrite_SignExtendedOffset, int idWrite_WriteRegister_20_16, int idWrite_WriteRegister_15_11, int idWrite_RFormatFunc) {
        // EX READ------------------------------------------------------------------------------------------------------
        /* EX Stage
            Here you'll perform the requested instruction on the specific operands you read out of the READ version of the IDEX pipeline register.
            Then, write the appropriate values to the WRITE version of the EX/MEM pipeline register.
         */
        this.exRead_ControlSignal = idWrite_ControlSignal;
        this.exRead_ProgramCounter = idWrite_ProgramCounter;
        this.exRead_ReadData1 = idWrite_ReadData1;
        this.exRead_ReadData2 = idWrite_ReadData2;
        this.exRead_SignExtendedOffset = idWrite_SignExtendedOffset;
        this.exRead_WriteRegister_20_16 = idWrite_WriteRegister_20_16;
        this.exRead_WriteRegister_15_11 = idWrite_WriteRegister_15_11;
        this.exRead_RFormatFunc = idWrite_RFormatFunc;

        System.out.println("\nEX Read");
        System.out.println("Incr PC  " + exRead_ProgramCounter);
        System.out.println("ReadReg1Value  " + Integer.toHexString(exRead_ReadData1));
        System.out.println("ReadReg2Value  " + Integer.toHexString(exRead_ReadData2));
        System.out.println("SEOffset  X");
        System.out.println("WriteReg_20_16  " + exRead_WriteRegister_20_16);
        System.out.println("WriteReg_15_11  " + exRead_WriteRegister_15_11);
        System.out.println("Function  " + Integer.toHexString(exRead_RFormatFunc));

        // EX WRITE-----------------------------------------------------------------------------------------------------
        exWrite_ControlSignal = exRead_ControlSignal;
        exWrite_SwValue = exRead_ReadData2;

        // ALU control
        if (exRead_ControlSignal.getAluOperation() == 0) {
            exRead_AluControl = 2;
        }
        // pull it from func
        else if (exRead_ControlSignal.getAluOperation() == 10) {

            int func = exRead_SignExtendedOffset & 0x003F;
            // 100,000 add --> 0010
            if (func == 0x20) {
                exRead_AluControl = 2;
            }
            // 100,010 subtract --> 0110
            else if (func == 0x22) {
                exRead_AluControl = 6;
            }
            // 100,100 and --> 0000
            else if (func == 0x24) {
                exRead_AluControl = 0;
            }
        }

        // do the ALU operation
        if (exRead_ControlSignal.getAluSource() == 1) { // this represents the MUX
            // when asserted, the second ALU operand is the sign extended, lower 16 bits of the instruction
            int aluControlOutput = exRead_SignExtendedOffset;

            if (exRead_AluControl == 2) {
                // add
                exWrite_AluResult = exRead_ReadData1 + aluControlOutput;
            } else if (exRead_AluControl == 6) {
                // subtract
                exWrite_AluResult = exRead_ReadData1 - aluControlOutput;
            }
        } else { // this represents the MUX
            // when not asserted, the second alu operand comes from the second register file output (read data 2)
            if (exRead_AluControl == 2) {
                // add
                exWrite_AluResult = exRead_ReadData1 + exRead_ReadData2;
            } else if (exRead_AluControl == 6) {
                // subtract
                exWrite_AluResult = exRead_ReadData1 - exRead_ReadData2;
            }
        }
        // EX RegDst MUX --> output: write reg num
        // when asserted, register destination for write come from bits 15:11
        if (exRead_ControlSignal.getRegisterDestination() == 1) {
            exWrite_WriteRegNum = exRead_WriteRegister_15_11;
            // when not asserted, register destination comes from bits 20:16
        } else if (exRead_ControlSignal.getRegisterDestination() == 0) {
            exWrite_WriteRegNum = exRead_WriteRegister_20_16;
        }
        System.out.println("\nEX Write");
        System.out.println("exWrite_CalcBTA  X");
        System.out.println("exWrite_AluZero  " + exWrite_AluZero);
        System.out.println("exWrite_AluResult  " + Integer.toHexString(exWrite_AluResult));
        System.out.println("exWrite_SwValue  " + Integer.toHexString(exWrite_SwValue));
        System.out.println("exWrite_WriteRegNum  " + exWrite_WriteRegNum);
    }
    /*
    // EX READ------------------------------------------------------------------------------------------------------
        /* EX Stage
            Here you'll perform the requested instruction on the specific operands you read out of the READ version of the IDEX pipeline register.
            Then, write the appropriate values to the WRITE version of the EX/MEM pipeline register.
            For example, an “add” operation will take the two operands out of the ID/EX pipeline register and add them together like this:
            EX_MEM_WRITE.ALU_Result = ID_EX_READ.Reg_Val1 + ID_EX_READ.Reg_Val2;

    pipeline.exRead_ControlSignal = pipeline.idWrite_ControlSignal;
    pipeline.exRead_ProgramCounter = pipeline.idWrite_ProgramCounter;
    pipeline.exRead_ReadData1 = pipeline.idWrite_ReadData1;
    pipeline.exRead_ReadData2 = pipeline.idWrite_ReadData2;
    pipeline.exRead_SignExtendedOffset = pipeline.idWrite_SignExtendedOffset;
    pipeline.exRead_WriteRegister_20_16 = pipeline.idWrite_WriteRegister_20_16;
    pipeline.exRead_WriteRegister_15_11 = pipeline.idWrite_WriteRegister_15_11;

        System.out.println("\nEX Read");
        System.out.println("Incr PC  " + pipeline.programCounter);
        System.out.println("ReadReg1Value  " + Integer.toHexString(pipeline.exRead_ReadData1));
        System.out.println("ReadReg2Value  " + Integer.toHexString(pipeline.exRead_ReadData2));
        System.out.println("SEOffset  X");
        System.out.println("WriteReg_20_16  " + pipeline.exRead_WriteRegister_20_16);
        System.out.println("WriteReg_15_11  " + pipeline.exRead_WriteRegister_15_11);
        System.out.println("Function  " + Integer.toHexString(instruction.rFormatFunc));


     */
    /*
    // EX WRITE-----------------------------------------------------------------------------------------------------
    pipeline.exWrite_ControlSignal = pipeline.exRead_ControlSignal;
    pipeline.exWrite_SwValue = pipeline.exRead_ReadData2;

    // ALU control
        if (pipeline.exRead_ControlSignal.getAluOperation() == 0) {
        pipeline.exRead_AluControl = 2;
    }
    // pull it from func
        else if (pipeline.exRead_ControlSignal.getAluOperation() == 10) {

        int func = pipeline.exRead_SignExtendedOffset & 0x003F;
        // 100,000 add --> 0010
        if (func == 0x20) {
            pipeline.exRead_AluControl = 2;
        }
        // 100,010 subtract --> 0110
        else if (func == 0x22) {
            pipeline.exRead_AluControl = 6;
        }
        // 100,100 and --> 0000
        else if (func == 0x24) {
            pipeline.exRead_AluControl = 0;
        }
    }

    // do the ALU operation
        if (pipeline.exRead_ControlSignal.getAluSource() == 1) { // this represents the MUX
        // when asserted, the second ALU operand is the sign extended, lower 16 bits of the instruction
        int aluControlOutput = pipeline.exRead_SignExtendedOffset;

        if (pipeline.exRead_AluControl == 2) {
            // add
            pipeline.exWrite_AluResult = pipeline.exRead_ReadData1 + aluControlOutput;
        } else if (pipeline.exRead_AluControl == 6) {
            // subtract
            pipeline.exWrite_AluResult = pipeline.exRead_ReadData1 - aluControlOutput;
        }
    } else { // this represents the MUX
        // when not asserted, the second alu operand comes from the second register file output (read data 2)
        if (pipeline.exRead_AluControl == 2) {
            // add
            pipeline.exWrite_AluResult = pipeline.exRead_ReadData1 + pipeline.exRead_ReadData2;
        } else if (pipeline.exRead_AluControl == 6) {
            // subtract
            pipeline.exWrite_AluResult = pipeline.exRead_ReadData1 - pipeline.exRead_ReadData2;
        }
    }
    // EX RegDst MUX --> output: write reg num
    // when asserted, register destination for write come from bits 15:11
        if (pipeline.exRead_ControlSignal.getRegisterDestination() == 1) {
        pipeline.exWrite_WriteRegNum = pipeline.exRead_WriteRegister_15_11;
        // when not asserted, register destination comes from bits 20:16
    } else if (pipeline.exRead_ControlSignal.getRegisterDestination() == 0) {
        pipeline.exWrite_WriteRegNum = pipeline.exRead_WriteRegister_20_16;
    }

        System.out.println("\nEX Write");
        System.out.println("exWrite_CalcBTA  X");
        System.out.println("exWrite_AluZero  " + pipeline.exWrite_AluZero);
        System.out.println("exWrite_AluResult  " + Integer.toHexString(pipeline.exWrite_AluResult));
        System.out.println("exWrite_SwValue  " + Integer.toHexString(pipeline.exWrite_SwValue));
        System.out.println("exWrite_WriteRegNum  " + pipeline.exWrite_WriteRegNum);
     */
}
