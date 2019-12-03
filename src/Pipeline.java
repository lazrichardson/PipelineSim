public class Pipeline {

    Memory memory;
    int cycleCount;
    int programCounter;
    int[] instructionCache = {
            /* 0x00000000,
             0x00625022, // added for testing
             0x00a63820, // added for testing
                                             */
            //0xa1020000,


            0x810AFFFC,
/*
            //0x00831820,

            0x01263820,
            0x01224820,
            0x81180000,
            0x81510010,
            0x00624022,

             */
            0x00000000,
            0x00000000,
            0x00000000,
            0x00000000
    };

    public Pipeline() {
        memory = new Memory();
        cycleCount = 0;
        programCounter = 0;

        ifWrite_ProgramCounter = 0; // IF start
        ifWrite_Instruction = 0; // IF start

        idRead_ProgramCounter = 0; // ID start
        idRead_Instruction = 0; // ID start

        idWrite_ControlSignal = new ControlSignal(new Instruction(0x00000000));
        idWrite_ProgramCounter = 0; // ID end
        idWrite_ReadData1 = 0; // ID end
        idWrite_ReadData2 = 0; // ID end
        idWrite_SignExtendedOffset = 0;// ID end
        idWrite_WriteRegister_20_16 = 0; // ID end
        idWrite_WriteRegister_15_11 = 0; // ID end
        idWrite_RFormatFunc = 0;
        // EX READ
        exRead_ProgramCounter = 0; // EX start
        exRead_ReadData1 = 0; // EX start
        exRead_ReadData2 = 0; // EX start
        exRead_SignExtendedOffset = 0;// EX start
        exRead_WriteRegister_20_16 = 0; // EX start
        exRead_WriteRegister_15_11 = 0; // EX start
        exRead_RFormatFunc = 0;
        exRead_AluOp = 0;
        exRead_AluControl = 0;
        // EX WRITE
        exWrite_CalcBTA = 0; // DON'T NEED TO IMPLEMENT
        exWrite_AluZero = 0; // EX end
        exWrite_AluResult = 0; // EX end
        exWrite_SwValue = 0; // EX end
        exWrite_WriteRegNum = 0; // EX end
        exWrite_ControlSignal = new ControlSignal(new Instruction(0x00000000));
        // MEM READ
        memRead_CalcBTA = 0; // DON'T NEED TO IMPLEMENT
        memRead_AluZero = 0; // EX start
        memRead_AluResult = 0; // EX start
        memRead_SwValue = 0; // EX start
        memRead_WriteRegNum = 0; // EX start
        // MEM WRITE
        memWrite_LWDataValue = 0; // mem end
        memWrite_AluResult = 0; // mem end
        memWrite_WriteRegNum = 0; // mem end
        // WB READ
        wbRead_LWDataValue = 0; // mem end
        wbRead_AluResult = 0; // mem end
        wbRead_WriteRegNum = 0; // mem end
    }


    //  IF Stage------------------------------------------------------------------------------------------------------
    int ifWrite_ProgramCounter; // IF start
    int ifWrite_Instruction; // IF start

    public void IF_stage() {

        int instruction;

        if (cycleCount > (instructionCache.length - 1)) {
            instruction = 0x00000000;
        } else {
            instruction = instructionCache[cycleCount];
        }

        ifWrite_ProgramCounter = programCounter;
        ifWrite_Instruction = instruction;
    }

    public void printIfWrite() {
        System.out.println("\nIF Write");
        System.out.println("Incr PC  " + Integer.toHexString(ifWrite_ProgramCounter));
        System.out.println("ifWrite_Instruction  " + Integer.toHexString(ifWrite_Instruction));
    }

    // ID Stage ------------------------------------------------------------------------------------------------------

    // ID READ
    int idRead_ProgramCounter; // ID start
    int idRead_Instruction; // ID start

    // ID WRITE
    ControlSignal idWrite_ControlSignal; // control signal
    int idWrite_ProgramCounter; // ID end
    int idWrite_ReadData1; // ID end
    int idWrite_ReadData2; // ID end
    int idWrite_SignExtendedOffset;// ID end
    int idWrite_WriteRegister_20_16; // ID end
    int idWrite_WriteRegister_15_11; // ID end
    int idWrite_RFormatFunc;
    Instruction idWriteInstruction = new Instruction(0x00000000);

    // ID read
    public void ID_stage() {
        this.idRead_ProgramCounter = ifWrite_ProgramCounter;
        this.idRead_Instruction = ifWrite_Instruction;
    }

    // ID write
    public void idWrite() {
        idWriteInstruction = new Instruction(idRead_Instruction);
        idWrite_ControlSignal = new ControlSignal(idWriteInstruction);
        idWrite_ProgramCounter = idRead_ProgramCounter;
        idWrite_SignExtendedOffset = idWriteInstruction.iFormatOffset;
        idWrite_ReadData1 = memory.Regs[idWriteInstruction.getRegSrcOne()];
        idWrite_ReadData2 = memory.Regs[idWriteInstruction.getRegSrcTwo()];
        idWrite_WriteRegister_15_11 = idWriteInstruction.rFormatRegDest;
        idWrite_WriteRegister_20_16 = idWriteInstruction.getRegSrcTwo();
        idWrite_RFormatFunc = idWriteInstruction.rFormatFunc;
    }

    public void printIdRead() {
        System.out.println("\nID Read");
        idWriteInstruction.printDetailCodes();
    }

    public void printIdWrite() {
        System.out.println("\nID Write");
        System.out.println("Incr PC  " + Integer.toHexString(idWrite_ProgramCounter));
        System.out.println("ReadReg1Value  " + Integer.toHexString(idWrite_ReadData1));
        System.out.println("ReadReg2Value  " + Integer.toHexString(idWrite_ReadData2));
        System.out.println("SEOffset  X");
        System.out.println("WriteReg_20_16  " + idWrite_WriteRegister_20_16);
        System.out.println("WriteReg_15_11  " + idWrite_WriteRegister_15_11);
        System.out.println("Function  " + Integer.toHexString(idWrite_RFormatFunc));
    }

    // EX Stage ------------------------------------------------------------------------------------------------------
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

    public void EX_stage() {
        exRead_ControlSignal = idWrite_ControlSignal;
        exRead_ProgramCounter = idWrite_ProgramCounter;
        exRead_ReadData1 = idWrite_ReadData1;
        exRead_ReadData2 = idWrite_ReadData2;
        exRead_SignExtendedOffset = idWrite_SignExtendedOffset;
        exRead_WriteRegister_20_16 = idWrite_WriteRegister_20_16;
        exRead_WriteRegister_15_11 = idWrite_WriteRegister_15_11;
        exRead_RFormatFunc = idWrite_RFormatFunc;
    }

    public void exWrite() {
        exWrite_ControlSignal = exRead_ControlSignal;
        exWrite_SwValue = exRead_ReadData2;

        // ALU control
        if (exWrite_ControlSignal.getAluOperation() == 0) {
            exRead_AluControl = 2;
        }
        // pull it from func
        else if (exWrite_ControlSignal.getAluOperation() == 10) {

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
        if (exWrite_ControlSignal.getAluSource() == 1) { // this represents the MUX
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
        if (exWrite_ControlSignal.getRegisterDestination() == 1) {
            exWrite_WriteRegNum = exRead_WriteRegister_15_11;
            // when not asserted, register destination comes from bits 20:16
        } else if (exWrite_ControlSignal.getRegisterDestination() == 0) {
            exWrite_WriteRegNum = exRead_WriteRegister_20_16;
        }
    }

    public void printExRead() {
        System.out.println("\nEX Read");
        System.out.println("Incr PC  " + Integer.toHexString(exRead_ProgramCounter));
        System.out.println("ReadReg1Value  " + Integer.toHexString(exRead_ReadData1));
        System.out.println("ReadReg2Value  " + Integer.toHexString(exRead_ReadData2));
        System.out.println("SEOffset  X");
        System.out.println("WriteReg_20_16  " + exRead_WriteRegister_20_16);
        System.out.println("WriteReg_15_11  " + exRead_WriteRegister_15_11);
        System.out.println("Function  " + Integer.toHexString(exRead_RFormatFunc));
    }

    public void printExWrite() {
        System.out.println("\nEX Write");
        System.out.println("exWrite_CalcBTA  X");
        System.out.println("exWrite_AluZero  " + exWrite_AluZero);
        System.out.println("exWrite_AluResult  " + Integer.toHexString(exWrite_AluResult));
        System.out.println("exWrite_SwValue  " + Integer.toHexString(exWrite_SwValue));
        System.out.println("exWrite_WriteRegNum  " + exWrite_WriteRegNum);
    }

    // MEM Stage ------------------------------------------------------------------------------------------------------
    /* MEM Stage
            If the instruction is a lb:
                1. use the address you calculated in the EX stage as an index into your Main Memory array
                2. get the value that is there.
            Else, pass information from the READ version of the EX_MEM pipeline register to the WRITE version of MEM_WB.
                             */
    // MEM READ
    ControlSignal memRead_ControlSignal; // control signal
    int memRead_CalcBTA; // DON'T NEED TO IMPLEMENT
    int memRead_AluZero; // EX start
    int memRead_AluResult; // EX start
    int memRead_SwValue; // EX start
    int memRead_WriteRegNum; // EX start

    // MEM WRITE
    ControlSignal memWrite_ControlSignal; // control signal
    int memWrite_LWDataValue; // mem end
    int memWrite_AluResult; // mem end
    int memWrite_WriteRegNum; // mem end

    public void MEM_stage() {
        // MEM READ-----------------------------------------------------------------------------------------------------
        this.memRead_AluZero = exWrite_AluZero;
        this.memRead_AluResult = exWrite_AluResult;
        this.memRead_SwValue = exWrite_SwValue;
        this.memRead_WriteRegNum = exWrite_WriteRegNum;
        this.memRead_ControlSignal = exWrite_ControlSignal;
    }

    public void memWrite() {
        // MEM WRITE----------------------------------------------------------------------------------------------------
        if (memWrite_ControlSignal.getMemoryRead() == 1) {
            memWrite_AluResult = memory.Main_Mem[exWrite_WriteRegNum];
        } else {
            memWrite_AluResult = memRead_AluResult;
        }
        memWrite_WriteRegNum = memRead_WriteRegNum;
        memWrite_ControlSignal = memRead_ControlSignal;

    }

    public void printMemRead() {
        System.out.println("\nMEM Read");
        System.out.println("memRead_CalcBTA  X");
        System.out.println("memRead_AluZero  " + memRead_AluZero);
        System.out.println("memRead_AluResult  " + Integer.toHexString(memRead_AluResult));
        System.out.println("memRead_SwValue  " + Integer.toHexString(memRead_SwValue));
        System.out.println("memRead_WriteRegNum  " + memRead_WriteRegNum);
    }

    public void printMemWrite() {
        System.out.println("\nMEM Write");
        System.out.println("memWrite_LWDataValue  " + memWrite_LWDataValue);
        System.out.println("memRead_AluResult  " + Integer.toHexString(memWrite_AluResult));
        System.out.println("memRead_WriteRegNum  " + memWrite_WriteRegNum);
    }

    /* WB STAGE
           Write to the registers based on information you read out of the READ version of MEM_WB.
         */
    // WB READ
    int wbRead_LWDataValue; // mem end
    int wbRead_AluResult; // mem end
    int wbRead_WriteRegNum; // mem end

    public void WB_stage() {
        memWrite_ControlSignal = memRead_ControlSignal;
        wbRead_LWDataValue = memWrite_LWDataValue;
        wbRead_AluResult = memWrite_AluResult;
        wbRead_WriteRegNum = memWrite_WriteRegNum;
    }

    public void wbWrite() {
        if (memWrite_ControlSignal.getRegisterWrite() == 1) {
            memory.Regs[memWrite_WriteRegNum] = memWrite_AluResult;
        }
    }

    public void printWbRead() {
        System.out.println("\nWB Read");
        System.out.println("wbRead_LWDataValue  " + wbRead_LWDataValue);
        System.out.println("wbRead_AluResult  " + Integer.toHexString(wbRead_AluResult));
        System.out.println("wbRead_WriteRegNum  " + wbRead_WriteRegNum);
    }

    public void Print_out_everything() {
        printIfWrite();
        printIdRead();
        printIdWrite();
        printExRead();
        printExWrite();
        printMemRead();
        printMemWrite();
        printWbRead();
        memory.regsPrint();
    }

    public void Copy_write_to_read() {
        IF_stage();
        idWrite();
        exWrite();
        memWrite();
        wbWrite();
    }

}






