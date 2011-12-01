package main

import "sync"

var optimize int = 25

func Merge(arr []int, group *sync.WaitGroup) {
	if len(arr) < optimize && len(arr) != 1 {
		InsertionSort(arr)
	} else if len(arr) > 2 {
		// Start at the midpoint
		size := len(arr) / 2

		left  := arr[:size]
		right := arr[size:]

		// Perform sub-merges
		group := new(sync.WaitGroup)
		group.Add(2)
			go Merge(left,  group)
			go Merge(right, group)
		group.Wait()

		// For holding results
		data := make([]int, len(arr))

		i := 0
		for len(left) > 0 && len(right) > 0 {
			// Decide which list to pull from
			if left[0] > right[0] {
				// Put the first value into the results and shrink the array
				data[i], right = right[0], right[1:]
			} else {
				// Same thing for the left
				data[i], left = left[0], left[1:]
			}
			i++ // Next element
		}

		// Figure out which list isn't empty
		var remainder []int

		if len(left) > 0 {
			remainder = left
		} else {
			remainder = right
		}

		// Empty remaining values
		for len(remainder) > 0 {
			data[i], remainder = remainder[0], remainder[1:]
			i++
		}

		// Copy result to our answer
		for i := 0; i < len(arr); i++ {
			arr[i] = data[i]
		}
	} else if len(arr) == 2 {
		if arr[0] > arr[1] {
			arr[0], arr[1] = arr[1], arr[0]
		}
	}
	group.Done()
}

func InsertionSort(arr []int) {
	for i, j := 1, 1; i < len(arr); i, j = i+1, i+1 {
		for j > 0 && arr[j] < arr[j-1] {
			arr[j-1], arr[j] = arr[j], arr[j-1]
			j--
		}
	}
}
