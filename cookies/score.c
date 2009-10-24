

#include <stdio.h>

int hash(char vec[6]) {
	int sorted[6];
	int i,j,k,s;

	for (i=0; i<6; sorted[k] = vec[i++]-'a')
		for (k=j=0; j<6; ++j)
			if (i!=j && vec[i]==vec[j]) return 0;
			else	k += vec[i]<vec[j];
	for (s=i=0; i<5; ++i) s = 10*s+sorted[i];

	return s;
}

main() {
	char	c;
	char	last5[6]={'@','@','@','@','@','@'};
	int	num_appear=0, hash_appear[100000];
	int	i,i5=0;

	for (i=0; i<100000; ++i) hash_appear[i]=!i;
	for (i=0; i<500 && (c=getchar()) <= 'j' && c>='a'; ++i) {
		last5[i5] = c;
		i5 = (i5+1)%5;
		num_appear += !hash_appear[hash(last5)]++;

	}

	return (num_appear<252) ? 0 : 500-i;

}

