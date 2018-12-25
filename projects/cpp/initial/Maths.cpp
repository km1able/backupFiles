

#include "Maths.hpp"
#include <iostream>

Maths::Maths()
{
    this->vector = glm::vec3(1.0f);
}

void Maths::printVec()
{
    std::cout << vector.x << " " << vector.y << " " << vector.z << std::endl;
}