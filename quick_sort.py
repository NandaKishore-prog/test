A = list(map(int,input("Enter the list").split()))
def quick_sort(array):
    if len(array)<=1:
        return array
    pivot=array[len(array) -1]
    left = [x for x in array  if x<pivot]
    middle= [x for x in array if x==pivot]
    right= [x for x in array if x>pivot]
    return quick_sort(left) + middle +quick_sort(right)
print(quick_sort(A))
