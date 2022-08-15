#include <stdio.h>
#include <stdlib.h>
int stick = 0;
int temp = 0;

void func(int n, int val)
{
	if (n == val)
		stick++;
	else if (n > val) {
		func(n / 2, val);
	}
	else {
		if ((temp + n) < val) {
			temp += n;
			stick++;
			func(n / 2, val);
		}
		else if ((temp + n) > val) {
			func(n / 2, val);
		}
		else if ((temp + n) == val) {
			stick++;
		}
	}
}

int main(void)
{
	int input;

	scanf("%d", &input);
	if (input > 64)
		return 0;

	func(64,input);

	printf("%d\n", stick);
}