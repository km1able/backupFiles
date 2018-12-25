#include <stdio.h>
#include <string.h>

const int BUFSIZE = 1;
int main()
{
    short int tempStore = 1000;
    //short int max is 32768
    long int daily = 380000;
    tempStore += daily;
    printf("% i", tempStore);
    return 0;
}