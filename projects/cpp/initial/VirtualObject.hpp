#ifndef VirtualObjecthpp
#define VirtualObjecthpp

class VirtualObject{

public: 

//constructor
VirtualObject();
VirtualObject(const int* val );

//destructor
~VirtualObject();

//copy constructor
VirtualObject( const VirtualObject & obj );



};

#endif