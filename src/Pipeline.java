public class Pipeline {

    int cycleCount = -1;
    int[] instructionCache = {
            0x00625022, // added for testing
            0x00a63820, // added for testing
            /*
            0xa1020000,
            0x810AFFFC,
            0x00831820,
            0x01263820,
            0x01224820,
            0x81180000,
            0x81510010,
            0x00624022,
            0x00000000,
            0x00000000,
            0x00000000,
            0x00000000
            */
            };


    public int getCycleCount() {
        return cycleCount;
    }

    public Memory getMemory() {
        return memory;
    }

    Memory memory = new Memory();

    public Pipeline(){

    };



/*
    // IF WRITE
    int ifWrite_ProgramCounter; // IF start
    int ifWrite_Instruction; // IF start

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

    // EX READ
    ControlSignal exRead_ControlSignal; // control signal
    int exRead_ProgramCounter; // EX start
    int exRead_ReadData1; // EX start
    int exRead_ReadData2; // EX start
    int exRead_SignExtendedOffset;// EX start
    int exRead_WriteRegister_20_16; // EX start
    int exRead_WriteRegister_15_11; // EX start
    int exRead_AluOp;
    int exRead_AluControl;

    // EX WRITE
    ControlSignal exWrite_ControlSignal; // control signal
    int exWrite_CalcBTA; // DON'T NEED TO IMPLEMENT
    int exWrite_AluZero; // EX end
    int exWrite_AluResult; // EX end
    int exWrite_SwValue; // EX end
    int exWrite_WriteRegNum; // EX end

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

    // WB READ
    int wbRead_LWDataValue; // mem end
    int wbRead_AluResult; // mem end
    int wbRead_WriteRegNum; // mem end

 */
}
