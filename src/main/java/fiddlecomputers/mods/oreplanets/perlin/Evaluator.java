package fiddlecomputers.mods.oreplanets.perlin;

public interface Evaluator
{

    double evalNoise(double x);

    double evalNoise(double x, double y);

    double evalNoise(double x, double y, double z);
}
