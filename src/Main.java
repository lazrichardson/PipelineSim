public class Main {

    public static void main(String[] args) {

        int[] initInstruction = {
                0x00000000
        };
        int[] instructionCache = {
                0x00000000,
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
        int controlBit = 0;
        int cycleCount = 0;
        int programCounter = 0;
        Memory memory = new Memory();
        IF_Stage if_stage = new IF_Stage(0, 0, initInstruction, 1);
        ID_Stage id_stage = new ID_Stage(if_stage, memory, 1);
        EX_Stage ex_stage = new EX_Stage(id_stage, 1);
        MEM_Stage mem_stage = new MEM_Stage(ex_stage, 1);
        WB_Stage wb_stage = new WB_Stage(mem_stage, memory, 1);

        while (cycleCount < instructionCache.length) {

            System.out.println("\n" + cycleCount + " ----------------------------------------------------------------------");
            // IF     ------------------------------------------------------------------------------------------------------
            if_stage = new IF_Stage(cycleCount, programCounter, instructionCache, 0);

            // ID     ------------------------------------------------------------------------------------------------------
            id_stage = new ID_Stage(if_stage, memory, 0);

            // EX     ------------------------------------------------------------------------------------------------------
            ex_stage = new EX_Stage(id_stage, 0);

            // MEM     -----------------------------------------------------------------------------------------------------
            mem_stage = new MEM_Stage(ex_stage, 0);

            // WB      -----------------------------------------------------------------------------------------------------
            wb_stage = new WB_Stage(mem_stage, memory, 0);

            System.out.println("\n" + cycleCount + " ----------------------------------------------------------------------");


            // increment all of the counters
            cycleCount = cycleCount + 1;
            programCounter = programCounter + 4;
        }
    }
}  // class end
