public class WB_Stage {
    /* WB STAGE
           Write to the registers based on information you read out of the READ version of MEM_WB.
         */
    // WB READ
    int wbRead_LWDataValue; // mem end
    int wbRead_AluResult; // mem end
    int wbRead_WriteRegNum; // mem end
    ControlSignal memWrite_ControlSignal;

    public WB_Stage() {
    }

    public void setWBStage(ControlSignal memRead_ControlSignal, int memWrite_LWDataValue, int memWrite_AluResult, int memWrite_WriteRegNum, Memory memory) {
        memWrite_ControlSignal = memRead_ControlSignal;
        wbRead_LWDataValue = memWrite_LWDataValue;
        wbRead_AluResult = memWrite_AluResult;
        wbRead_WriteRegNum = memWrite_WriteRegNum;

        if (memWrite_ControlSignal.getRegisterWrite() == 1) {
            memory.Regs[memWrite_WriteRegNum] = memWrite_AluResult;
        }
    }

    public void printWBStage() {
        System.out.println("\nWB Read");
        System.out.println("wbRead_LWDataValue  " + wbRead_LWDataValue);
        System.out.println("wbRead_AluResult  " + Integer.toHexString(wbRead_AluResult));
        System.out.println("wbRead_WriteRegNum  " + wbRead_WriteRegNum);
    }
}
