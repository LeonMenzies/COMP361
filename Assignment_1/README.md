# Assignment 1
### 5.5
write and O(n lg n) algorithm that receives as input an array a of 
n real numbers and a value val. The algorithm returns true if 
there are distinct indexes i and j such that a[i] + a[j] = val
and false otherwise.

findVal(a[], n, val)
    sort(a)
    i <- 0
    j <- n - 1
    while( i < j)
        if(a[i] + a[j] == val)
            return true
        else if(a[i] + a[j]) < val)
            i++
        else
            j--
    return false


