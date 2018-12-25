#include <stdio.h>
#include <string.h>

const int BUFSIZE = 1000;
int main()
{
    //prinf() displays the string inside quotation
    printf("Hello, World!");
    char buf[BUFSIZE];
    strcpy(buf, "This will not overflow");
    printf("%s", buf);
    return 0;
}