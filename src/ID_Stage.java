public class ID_Stage {

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
    Instruction instruction;

    public ID_Stage(IF_Stage if_stage, Memory memory, int init) {

        this.idRead_ProgramCounter = if_stage.ifWrite_ProgramCounter;
        this.idRead_Instruction = if_stage.ifWrite_Instruction;

        instruction = new Instruction(idRead_Instruction);

        this.idWrite_ControlSignal = new ControlSignal(instruction);

        idWrite_ProgramCounter = idRead_ProgramCounter;
        idWrite_SignExtendedOffset = instruction.iFormatOffset;

        idWrite_ReadData1 = memory.Regs[instruction.getRegSrcOne()];
        idWrite_ReadData2 = memory.Regs[instruction.getRegSrcTwo()];

        idWrite_WriteRegister_15_11 = instruction.rFormatRegDest;
        idWrite_WriteRegister_20_16 = instruction.getRegSrcTwo();

        idWrite_RFormatFunc = instruction.rFormatFunc;

        if(init == 0) {
            printIdWrite();
        }
    }

    public void printIdWrite() {
        System.out.println("\nID Read");
        instruction.printDetailCodes();

        System.out.println("\nID Write");
        System.out.println("Incr PC  " + Integer.toHexString(idWrite_ProgramCounter));
        System.out.println("ReadReg1Value  " + Integer.toHexString(idWrite_ReadData1));
        System.out.println("ReadReg2Value  " + Integer.toHexString(idWrite_ReadData2));
        System.out.println("SEOffset  X");
        System.out.println("WriteReg_20_16  " + idWrite_WriteRegister_20_16);
        System.out.println("WriteReg_15_11  " + idWrite_WriteRegister_15_11);
        System.out.println("Function  " + Integer.toHexString(idWrite_RFormatFunc));
    }
}
