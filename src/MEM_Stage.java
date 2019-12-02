public class MEM_Stage {
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

    public MEM_Stage(EX_Stage ex_stage, int init) {
        // MEM READ-----------------------------------------------------------------------------------------------------
        this.memRead_AluZero = ex_stage.exWrite_AluZero;
        this.memRead_AluResult = ex_stage.exWrite_AluResult;
        this.memRead_SwValue = ex_stage.exWrite_SwValue;
        this.memRead_WriteRegNum = ex_stage.exWrite_WriteRegNum;
        this.memRead_ControlSignal = ex_stage.exWrite_ControlSignal;
        // MEM WRITE----------------------------------------------------------------------------------------------------
        memWrite_AluResult = memRead_AluResult;
        memWrite_WriteRegNum = memRead_WriteRegNum;
        memWrite_ControlSignal = memRead_ControlSignal;

        if (init == 0) {
            printMemRead();
            printMemWrite();
        }
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
}
