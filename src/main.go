package main

import (
	"fmt"
	"rand"
	"runtime"
	"sync"
	"time"
)

func main() {
	runtime.GOMAXPROCS(4)
	data := []int{}
	for i := 0; i < 10000000; i++ {
		data = append(data, rand.Intn(1000000))
	}

	done := new(sync.WaitGroup)
	done.Add(1)
	start := time.Nanoseconds()
	Merge(data, done)
	done.Wait()
	fmt.Println(time.Nanoseconds() - start)
	// Run again on already sorted data.
	done.Add(1)
	start = time.Nanoseconds()
	Merge(data, done)
	done.Wait()
	fmt.Println(time.Nanoseconds() - start)
}
