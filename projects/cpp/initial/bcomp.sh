INCLUDEPTH='/usr/local/lib/boost_1_67_0'
LIBPTH='/usr/local/lib/boost_1_67_0/stage/lib'
c++ -I $INCLUDEPTH -Wall main.cpp Triangle.cpp Maths.cpp IShape2D.hpp \
-L$LIBPTH -lboost_thread -lboost_system -lpthread -lboost_thread.so.1.67.0
mv a.out tria
./tria
