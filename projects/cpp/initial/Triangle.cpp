
#include <iostream>
#include "Triangle.hpp"

Triangle::Triangle()
{
    this->x = 0;
    this->y = 0;
}

Triangle::Triangle(float x, float y)
{
    this->x = x;
    this->y = y;
}

void Triangle::checkProtect()
{
    std::cout << "This is a public method inherited from protected in Shape2D";
    std::cout << std::endl;
}

float Triangle::getSize()
{
    std::cout << "1/2 Base*height..." << std::endl;
    return 0;
}

void Triangle::printVal()
{
    std::cout << "X: " << x << " Y: " << y << std::endl;
}

void Triangle::setXY(float x, float y)
{
    this->x = x;
    this->y = y;
}