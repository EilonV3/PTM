package pipeGame.server;

public class PipeGameTask implements Runnable , Comparable<PipeGameTask> {
	private int size;
    private Runnable r;

    public PipeGameTask(Runnable r, int size) {
        this.r = r;
        this.size = size;
    }

    @Override
    public void run() {
        r.run();
    }

    @Override
    public int compareTo(PipeGameTask o) {
        return Integer.compare(this.size, o.size);
    }
}
