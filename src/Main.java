public class Main {

    public static void main(String[] args) {

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

        while (cycleCount < instructionCache.length) {

            System.out.println("\n" + cycleCount + " ----------------------------------------------------------------------");

            // IF     ------------------------------------------------------------------------------------------------------
            IF_Stage if_stage = new IF_Stage(cycleCount, programCounter, instructionCache);

            // ID     ------------------------------------------------------------------------------------------------------
            ID_Stage id_stage = new ID_Stage(if_stage, memory);

            // EX     ------------------------------------------------------------------------------------------------------
            EX_Stage ex_stage = new EX_Stage(id_stage);

            // MEM     -----------------------------------------------------------------------------------------------------
            MEM_Stage mem_stage = new MEM_Stage(ex_stage);

            // WB      -----------------------------------------------------------------------------------------------------
            WB_Stage wb_stage = new WB_Stage(mem_stage, memory);

            System.out.println("\n" + cycleCount + " ----------------------------------------------------------------------");


            // increment all of the counters
            cycleCount = cycleCount + 1;
            programCounter = programCounter + 4;
        }
    }
}  // class end
