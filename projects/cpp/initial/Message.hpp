#ifndef Messagehpp
#define Messagehpp

class Message{

public: 

//constructor
Message();
Message(const int* val );

//destructor
~Message();

//copy constructor
Message( const Message & obj );



};

#endif