
/** Virtual class for templating for other shapes (2D) **/
#ifndef IShape2Dhpp
#define IShape2Dhpp
class IShape2D
{

  public:
    virtual void checkProtect() = 0;
    virtual float getSize() = 0;
    virtual void printVal() = 0;
    virtual void setXY(float x, float y) = 0;
};

#endif