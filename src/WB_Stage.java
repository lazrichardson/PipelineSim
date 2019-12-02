public class WB_Stage {
    /* WB STAGE
           Write to the registers based on information you read out of the READ version of MEM_WB.
         */
    int cycleCount;
    // WB READ
    int wbRead_LWDataValue; // mem end
    int wbRead_AluResult; // mem end
    int wbRead_WriteRegNum; // mem end
    ControlSignal memWrite_ControlSignal;

    public WB_Stage(MEM_Stage mem_stage, Memory memory, int init, int cycleCount) {
        this.cycleCount = cycleCount;
        memWrite_ControlSignal = mem_stage.memRead_ControlSignal;
        wbRead_LWDataValue = mem_stage.memWrite_LWDataValue;
        wbRead_AluResult = mem_stage.memWrite_AluResult;
        wbRead_WriteRegNum = mem_stage.memWrite_WriteRegNum;

        if (memWrite_ControlSignal.getRegisterWrite() == 1) {
            memory.Regs[mem_stage.memWrite_WriteRegNum] = mem_stage.memWrite_AluResult;
        }

        if(init == 0) {
            printWBStage();
        }
    }

    public void printWBStage() {
        System.out.println("\nWB Read");
        System.out.println("wbRead_LWDataValue  " + wbRead_LWDataValue);
        System.out.println("wbRead_AluResult  " + Integer.toHexString(wbRead_AluResult));
        System.out.println("wbRead_WriteRegNum  " + wbRead_WriteRegNum);
    }
}
