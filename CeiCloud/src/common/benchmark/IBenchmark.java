package common.benchmark;

public interface IBenchmark {
	/**
	 * Runs the benchmark.
	 */
	void run() throws BenchmarkRunException;
}
