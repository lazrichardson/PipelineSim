public class Main {

    public static void main(String[] args) {

        int[] initInstruction = {
                0x00000000
        };
        int[] instructionCache = {
                /* 0x00000000,
                 0x00625022, // added for testing
                 0x00a63820, // added for testing
                                                 */
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

        };
        int controlBit = 0;
        int cycleCount = 0;
        int programCounter = 0;
        Memory memory = new Memory();
        IF_Stage if_stage = new IF_Stage(0, 0, initInstruction, 1);
        ID_Stage id_stage = new ID_Stage(if_stage, memory, 1, 0);
        EX_Stage ex_stage = new EX_Stage(id_stage, 1, 0);
        MEM_Stage mem_stage = new MEM_Stage(ex_stage, 1, 0);
        WB_Stage wb_stage = new WB_Stage(mem_stage, memory, 1, 0);

        IF_Stage if_stage_out = new IF_Stage(0, 0, initInstruction, 1);
        ID_Stage id_stage_out = new ID_Stage(if_stage, memory, 1, 0);
        EX_Stage ex_stage_out = new EX_Stage(id_stage, 1, 0);
        MEM_Stage mem_stage_out = new MEM_Stage(ex_stage, 1, 0);
        WB_Stage wb_stage_out = new WB_Stage(mem_stage, memory, 1, 0);

        while (cycleCount < instructionCache.length) {

            System.out.println("\n" + cycleCount + " ----------------------------------------------------------------------");
            // IF     ------------------------------------------------------------------------------------------------------
            if_stage = new IF_Stage(cycleCount, programCounter, instructionCache, 0);

            // ID     ------------------------------------------------------------------------------------------------------
            if (cycleCount > 0)
                id_stage = new ID_Stage(if_stage_out, memory, 0, cycleCount);
            if_stage_out = if_stage;

            // EX     ------------------------------------------------------------------------------------------------------
            ex_stage = new EX_Stage(id_stage_out, 0, cycleCount);
            id_stage_out = id_stage;

            // MEM     -----------------------------------------------------------------------------------------------------
            mem_stage = new MEM_Stage(ex_stage_out, 0, cycleCount);
            ex_stage_out = ex_stage;
            // WB      -----------------------------------------------------------------------------------------------------
            wb_stage = new WB_Stage(mem_stage_out, memory, 0, cycleCount);
            mem_stage_out = mem_stage;

            System.out.println("\n" + cycleCount + " ----------------------------------------------------------------------");

            // increment all of the counters
            cycleCount = cycleCount + 1;
            programCounter = programCounter + 4;

            memory.regsPrint();

        }

    }
}  // class end
