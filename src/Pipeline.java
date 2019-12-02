public class Pipeline {

    Memory memory = new Memory();
    int cycleCount;
    int[] instructionCache = {
            0x00000000,
            0x00a63820, // added for testing
            0x00000000,
            0x00625022 // added for testing
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

    public Pipeline() {
        this.cycleCount = 0;
    }

    // IF
    IF_Stage if_stage = new IF_Stage();

    // ID
    ID_Stage id_read = new ID_Stage();
    ID_Stage id_write = new ID_Stage();

    // EX
    EX_Stage ex_read = new EX_Stage();
    EX_Stage ex_write = new EX_Stage();

    // MEM
    MEM_Stage mem_read = new MEM_Stage();
    MEM_Stage mem_write = new MEM_Stage();

    // WB
    WB_Stage wb_read = new WB_Stage();
    WB_Stage wb_write = new WB_Stage();
}
