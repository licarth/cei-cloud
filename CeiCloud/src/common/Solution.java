package common;

public interface Solution<P extends Problem, A extends Algorithm<P>> {
	int getCost();
}