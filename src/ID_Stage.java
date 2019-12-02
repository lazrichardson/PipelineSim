public class ID_Stage {

    // ID READ
    int idRead_ProgramCounter; // ID start
    int idRead_Instruction; // ID start

    public ControlSignal getIdWrite_ControlSignal() {
        return idWrite_ControlSignal;
    }

    public int getIdWrite_ProgramCounter() {
        return idWrite_ProgramCounter;
    }

    public int getIdWrite_ReadData1() {
        return idWrite_ReadData1;
    }

    public int getIdWrite_ReadData2() {
        return idWrite_ReadData2;
    }

    public int getIdWrite_SignExtendedOffset() {
        return idWrite_SignExtendedOffset;
    }

    public int getIdWrite_WriteRegister_20_16() {
        return idWrite_WriteRegister_20_16;
    }

    public int getIdWrite_WriteRegister_15_11() {
        return idWrite_WriteRegister_15_11;
    }

    // ID WRITE
    public ID_Stage() {
    }

    ControlSignal idWrite_ControlSignal; // control signal
    int idWrite_ProgramCounter; // ID end
    int idWrite_ReadData1; // ID end
    int idWrite_ReadData2; // ID end
    int idWrite_SignExtendedOffset;// ID end
    int idWrite_WriteRegister_20_16; // ID end
    int idWrite_WriteRegister_15_11; // ID end

    public int getIdWrite_RFormatFunc() {
        return idWrite_RFormatFunc;
    }

    int idWrite_RFormatFunc;

    public void setIDStage(int ifWrite_ProgramCounter, int ifWrite_Instruction, Memory memory) {

        this.idRead_ProgramCounter = ifWrite_ProgramCounter;
        this.idRead_Instruction = ifWrite_Instruction;

        Instruction instruction = new Instruction(idRead_Instruction);
        this.idWrite_ControlSignal = new ControlSignal(instruction);

        System.out.println("\nID Read");
        instruction.printDetailCodes();

        idWrite_ControlSignal.printControlSignal();
        idWrite_ProgramCounter = idRead_ProgramCounter;
        idWrite_SignExtendedOffset = instruction.iFormatOffset;
        idWrite_ReadData1 = memory.Regs[instruction.getRegSrcOne()];
        idWrite_ReadData2 = memory.Regs[instruction.getRegSrcTwo()];
        idWrite_WriteRegister_15_11 = instruction.rFormatRegDest;
        idWrite_WriteRegister_20_16 = instruction.getRegSrcTwo();
        idWrite_RFormatFunc = instruction.rFormatFunc;
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

    /*
    /* ID Stage
            Here you'll read an instruction from the READ version of IF/ID pipeline register
            1. do the decoding and register fetching
            2. write the values to the WRITE version of the ID/EX pipeline register.

     */
    /*
    // ID READ------------------------------------------------------------------------------------------------------
    pipeline.idRead_ProgramCounter = pipeline.ifWrite_ProgramCounter;
    pipeline.idRead_Instruction = pipeline.ifWrite_Instruction;

    Instruction instruction = new Instruction(pipeline.idRead_Instruction);
    pipeline.idWrite_ControlSignal = new ControlSignal(instruction);

        System.out.println("\nID Read");
        instruction.printDetailCodes();

        pipeline.idWrite_ControlSignal.printControlSignal();
        pipeline.idWrite_ProgramCounter = pipeline.idRead_ProgramCounter;
        pipeline.idWrite_SignExtendedOffset = instruction.iFormatOffset;
        pipeline.idWrite_ReadData1 = memory.Regs[instruction.getRegSrcOne()];
        pipeline.idWrite_ReadData2 = memory.Regs[instruction.getRegSrcTwo()];
        pipeline.idWrite_WriteRegister_15_11 = instruction.rFormatRegDest;
        pipeline.idWrite_WriteRegister_20_16 = instruction.getRegSrcTwo();

        System.out.println("\nID Write");
        System.out.println("Incr PC  " + pipeline.programCounter);
        System.out.println("ReadReg1Value  " + Integer.toHexString(pipeline.idWrite_ReadData1));
        System.out.println("ReadReg2Value  " + Integer.toHexString(pipeline.idWrite_ReadData2));
        System.out.println("SEOffset  X");
        System.out.println("WriteReg_20_16  " + pipeline.idWrite_WriteRegister_20_16);
        System.out.println("WriteReg_15_11  " + pipeline.idWrite_WriteRegister_15_11);
        System.out.println("Function  " + Integer.toHexString(instruction.rFormatFunc));
     */

}
