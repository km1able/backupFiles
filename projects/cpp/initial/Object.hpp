#ifndef Objecthpp
#define Objecthpp

class Object
{

  public:
    //constructor
    Object();
    Object(const int *val);

    //destructor
    ~Object();

    //copy constructor
    Object(const Object &obj);
};

#endif