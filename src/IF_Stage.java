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

    public IF_Stage(int ifRead_ProgramCounter, int instruction) {

        this.ifWrite_ProgramCounter = ifRead_ProgramCounter;
        this.ifWrite_Instruction = instruction;

        System.out.println("\nIF Write\n" + Integer.toHexString(ifWrite_Instruction));
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
