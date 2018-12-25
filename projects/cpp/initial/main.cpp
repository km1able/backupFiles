#include "Triangle.hpp"
#include "Maths.hpp"
#include <iostream>
#include "glm/vec2.hpp"
#include "glm/vec3.hpp"

#include <boost/fiber/all.hpp>
#include <boost/atomic.hpp>
#include <utility>
#include <boost/lambda/lambda.hpp>
#include <atomic>
#include <iterator>
#include <algorithm>
#include <boost/foreach.hpp>
#include <boost/thread/thread.hpp>
#include <boost/thread.hpp>
#include <boost/asio/io_service.hpp>
#include <boost/bind.hpp>
#include <boost/coroutine2/all.hpp>

//#define BOOST_THREAD_USE_LIB

using namespace std;

//#include "IShape2D.h"
void testFn()
{
    std::cout << "hello from test function" << endl;
}
void testFun(int x)
{
    cout << "Entering testFun with x=: " << x << endl;
    for (int i = 0; i < x; i++)
    {
        cout << " " << i << " ";
    }
    cout << endl;
}
int main()
{
    using namespace boost::lambda;
    typedef std::istream_iterator<int> in;

    /* 
    std::for_each(
        in(std::cin), in(), std::cout << (_1 * 3) << " ");
*/
    //thread pool
    glm::vec2 myVec(3.4, 3.2);
    //  glm::vec3 myVec3(3.5, 2.3, 2.3);

    /*
    std::cout.setf(std::ios::boolalpha);
    boost::atomic<glm::vec3> myVec3;
    std::cout << myVec3.is_lock_free() << std::endl;
    //std::cout << "is vec3 lock free? " << std::atomic<glm::vec3>{}.is_lock_free() << std::endl;

    // std::cout << std::boolalpha << "is vec2 lock free? " << std::atomic<glm::vec2>{}.is_lock_free()
    //         << " is vec3 lock free?" << std::atomic<glm::vec3>{}.is_lock_free() << std::endl;
    */
    std::cout << boost::this_thread::get_id() << std::endl;
    static unsigned int Num_Threads = boost::thread::hardware_concurrency();
    std::cout << "Max threads: " << Num_Threads << std::endl;
    boost::thread t1(&testFn);
    t1.join();

    boost::asio::io_service ioService;
    boost::thread_group threadpool;

    boost::asio::io_service::work work(ioService);
    threadpool.create_thread(boost::bind(&boost::asio::io_service::run, &ioService));
    threadpool.create_thread(boost::bind(&boost::asio::io_service::run, &ioService));
    threadpool.create_thread(boost::bind(&boost::asio::io_service::run, &ioService));
    threadpool.create_thread(boost::bind(&boost::asio::io_service::run, &ioService));

    //  ioService.post(boost::bind(testFun, 1200));
    // ioService.post(boost::bind(testFun, 333));
    //  ioService.post(boost::bind(testFn));
    // ioService.post(boost::bind(testFun, 1000));

    std::string hello("Hello World. My Name is Kelly. This is a test of async io");
    BOOST_FOREACH (char ch, hello)
    {
        std::cout << ch;
    }
    std::cout << std::endl;
    Maths math;
    Maths *math1 = new Maths();

    math.printVec();
    math1->printVec();
    Triangle tri1;
    Triangle tri2(1.2, 1.3);

    Triangle *tri3 = new Triangle(3.2, 4.2);

    tri1.printVal();
    tri2.printVal();
    tri3->printVal();

    ioService.stop();
    threadpool.join_all();
    return 0;
}