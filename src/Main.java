public class Main {

    public static void main(String[] args) {

        Pipeline pipeline = new Pipeline();

        while (pipeline.cycleCount < pipeline.instructionCache.length) {

            System.out.println("\nClock Cycle " + pipeline.cycleCount + " ----------------------------------------------------------------------");

            pipeline.IF_stage();
            pipeline.ID_stage();
            pipeline.EX_stage();
            pipeline.MEM_stage();
            pipeline.WB_stage();

            pipeline.Print_out_everything();
            pipeline.Copy_write_to_read();

            // increment all of the counters
            pipeline.cycleCount = pipeline.cycleCount + 1;
            pipeline.programCounter = pipeline.programCounter + 4;
        }
    }
}  // class end
