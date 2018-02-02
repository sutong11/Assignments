import unittest

def remove_duplicate(arr, n):
    newarr = []
    counter_dict = {}
    for item in arr:
        if counter_dict.has_key(item):
            if counter_dict[item] == n-1:
                counter_dict[item] += 1
            else:
                newarr.append(item)
                counter_dict[item] +=1

        else:
            counter_dict[item] = 1
            newarr.append(item)

    return newarr


class functionTest(unittest.TestCase):
    def test_normal(self):
        self.assertEqual(remove_duplicate(array, n), [1, 2, 2, 3, 5, 4, 5, 2])

    def test_rev_0th(self):
        test_arr = [1, 2, 3, 1, 2, 3]
        test_n = 0
        self.assertEqual(remove_duplicate(test_arr, test_n), [1, 2, 3, 1, 2, 3])

    def test_rev_100th(self):
        test_arr = [1, 2, 3, 1, 2, 3]
        test_n = 100
        self.assertEqual(remove_duplicate(test_arr, test_n), [1, 2, 3, 1, 2, 3])


if __name__ == "__main__":
    #Input:
    #Input array:
    array = [1, 2, 2, 3, 5, 2, 4, 5, 5, 2]
    #the integer n:
    n = 3
    
    arr = remove_duplicate(array, n)

    print ("Input array: {0}".format(array))
    print ("Remove the {0}th duplicats in the array".format(n))
    print ("OutPut array: {0}".format(arr))

    unittest.main()


