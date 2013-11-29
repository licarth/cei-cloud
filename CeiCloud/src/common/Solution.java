package common;

public interface Solution<I extends Instance> {
	int getCost();
	I getInstance();
}