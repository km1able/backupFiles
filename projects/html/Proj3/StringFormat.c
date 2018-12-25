#include <stdio.h>
#include <string.h>

const int BUFSIZE = 128;
int main(int argc, char **argv)
{

    char buf[BUFSIZE];
    snprintf(buf, 128, argv[1]);
    printf("%s", buf);
    return 0;
}