#ifndef Trianglehpp
#define Trianglehpp
#include "IShape2D.hpp"

class Triangle : IShape2D
{
  private:
    float x, y;

  public:
    Triangle();
    Triangle(float x, float y);
    void checkProtect();
    float getSize();
    void printVal();
    void setXY(float x, float y);

}; //end: Triangle

#endif