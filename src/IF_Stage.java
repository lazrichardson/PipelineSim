public class IF_Stage {
    /*  IF Stage
            You will fetch the next instruction out of the Instruction Cache.
            Put it in the WRITE version of the IF/ID pipeline register.
             */

    // IF WRITE
    int ifWrite_ProgramCounter; // IF start
    int ifWrite_Instruction; // IF start

    public IF_Stage() {
    }

    public IF_Stage(int cycleCount, int programCounter, int[] instructionCache, int init) {

        int instruction;

        if (cycleCount > (instructionCache.length - 1)) {
            instruction = 0x00000000;
        } else {
            instruction = instructionCache[cycleCount];
        }

        this.ifWrite_ProgramCounter = programCounter;
        this.ifWrite_Instruction = instruction;

        if(init == 0) {
            System.out.println("\nIF Write\n" + Integer.toHexString(ifWrite_Instruction));
        }
    }

    /*
            // IF     ------------------------------------------------------------------------------------------------------
          IF Stage
        You will fetch the next instruction out of the Instruction Cache.
        Put it in the WRITE version of the IF/ID pipeline register.

    pipeline.ifWrite_ProgramCounter = pipeline.programCounter++;
    pipeline.ifWrite_Instruction = pipeline.instructionCache[pipeline.programCounter];
        System.out.println("\nIF Write\n" + Integer.toHexString(pipeline.ifWrite_Instruction));

     */

}
