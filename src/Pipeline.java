public class Pipeline {

    Memory memory = new Memory();
    int controlBit;
    int cycleCount;
    int programCounter = 0x7A000;
    IF_Stage if_stage;
    ID_Stage id_read;
    ID_Stage id_write;
    EX_Stage ex_read;
    EX_Stage ex_write;
    MEM_Stage mem_read;
    MEM_Stage mem_write;
    WB_Stage wb_read;
    WB_Stage wb_write;
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

    public Pipeline() {
        this.cycleCount = 0;
        if_stage = new IF_Stage();
        id_read = new ID_Stage();
        id_write = new ID_Stage();
        ex_read = new EX_Stage();
        ex_write = new EX_Stage();
        mem_read = new MEM_Stage();
        mem_write = new MEM_Stage();
        wb_read = new WB_Stage();
        wb_write = new WB_Stage();
    }

    // IF
    public void createIfStage() {

        int instruction;
        if (cycleCount > (instructionCache.length - 1)) {
            instruction = 0x00000000;
        } else {
            instruction = instructionCache[cycleCount];
        }
        this.if_stage = new IF_Stage(programCounter, instruction);
    }

    // ID
    public void createIdReadStage() {
        this.id_read = new ID_Stage(if_stage.ifWrite_ProgramCounter, if_stage.ifWrite_Instruction, memory);
    }
}
