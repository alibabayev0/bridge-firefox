# Mozilla Firefox: Optimized Fork

## Introduction

This repository is a fork of mozilla-firefox/reference-browser, specifically tailored for performance optimizations. Below, you'll find a summary of the enhancements made, categorized by their effects on startup times and resource usage. These optimizations were implemented locally on the Mozilla Android components to address issues and enhance performance.

## Optimizations Implemented

### Memory Management Enhancements
- Fixed a memory leak related to domain handling, ensuring more efficient use of memory and reducing potential performance bottlenecks.
- Removed redundant session states from memory, which contributes to a leaner memory footprint during browser operation.

### UI and Rendering Optimizations
- Implemented UI enhancements that accelerate the first render, improving the perceived startup speed and responsiveness of the browser.

### Initialization and Runtime Efficiency
- Introduced initializers and lazy initialization for GeckoRuntime, which delays the loading of certain components until they are actually needed, thereby optimizing startup times and resource consumption.
- Expanded the lazy initialization approach to various components, further streamlining the startup process and reducing initial resource demands.
- Refined the `warmUp()` method in the EngineProvider to utilize lazy initialization, aligning with the broader strategy to enhance performance.

### Project Setup and Benchmarking
- Set up the repository with essential tools like StartupBenchmark, BaselineProfiles, and LeakCanary, enabling thorough analysis and measurement of performance metrics to guide further optimizations.


### Startup Time Optimization

*Hot Startup: Time taken when the application is launched frequently.*
*Cold Startup: Time taken when the application is launched for the first time or after a long idle
period.*
*Warm Startup: Time taken when the application is launched after being recently used.*

### Cold Startup

| Metric | Before Optimization (ms) | After Optimization (ms) | Reduced       |
|--------|--------------------------|-------------------------|---------------|
| p50    | 450.3                    | 367.3                   | 18.4 %        |
| p90    | 470.2                    | 428.8                   | 8.8 %         |
| p99    | 518.8                    | 457.7                   | 11.7 %        |
| ...    | ...                      | ...                     | ...           |

### Resource Usage Optimization

| Metric       | RAM Usage Before | RAM Usage After | CPU Usage Before | CPU Usage After |
|--------------|------------------|-----------------|------------------|-----------------|
| 0 Tab Used   | ~188.9 MB        | ~187.2 MB       | -                | -               |
| 1 Tab Used   | ~256.4 MB        | ~208 MB         | -                | -               |
| 5 Tab Used   | ~311 MB          | ~210 MB         | ...              | ...             |
| 10 Tab Used  | ~383.4 MB        | ~211.3 MB       | ...              | ...             |
| 50 Tab Used  | ~462 MB          | ~235.7 MB       | ...              | ...             |
| 100 Tab Used | ~560.4 MB        | ~242.9 MB       | ...              | ...             |

*Note: 'Before' and 'After' columns represent the metrics before and after the optimizations were
applied.*

## Detailed Optimizations

### Lazy Initialization Implementation

- **Lazy Initialization**: Implemented lazy initialization across the entire application to delay the instantiation of objects until they are strictly needed. This approach minimizes the initial load time and reduces resource consumption during startup, enhancing the overall performance and responsiveness of the browser.
- **Initializers**: to streamline component initialization, enhancing resource utilization and startup performance.
- **UI improvements**: Implemented UI improvements by inflating views in a separate thread to prevent blocking the main thread, thereby enhancing responsiveness and user experience.
- **Fixed Memory Leaks (Increase RAM)**: opt: Domains memory leak and remove useless session states in memory

### Methodology

### Memory Leak Prevention and Session State Optimization

To address memory leaks related to domain handling and reduce unnecessary memory usage, a custom `BoundedUniqueList` container was implemented. This container maintains a bounded list of unique elements, ensuring efficient memory management and preventing the accumulation of unnecessary session states.

#### BoundedUniqueList Implementation

The `BoundedUniqueList` class provides the following functionality:

- **Capacity Limitation**: Allows specifying a maximum capacity for the list to prevent excessive memory usage.
- **Duplicate Element Handling**: Automatically removes duplicate elements and moves existing elements to the end of the list upon addition.
- **Automatic Removal of Oldest Elements**: When adding new elements exceeds the list's capacity, the oldest element is automatically removed from memory.

By utilizing the `BoundedUniqueList` container wherever session states are managed, redundant data is effectively removed from memory, resulting in optimized heap usage and improved overall performance.

#### Streamlined Asset Loading

Implemented optimized asset loading by reading files line by line, reducing memory overhead and improving performance.

#### Domains memory management issue

I discovered a memory management issue in android-components. Domains and asset lists are loaded into memory but not cleared, resulting in repetitive loading, failure to deallocate memory on app closure, and excessive RAM usage. I've implemented a fix to clear these resources after use, preventing redundant loading and improving memory efficiency. I plan to submit this as a bug fix pull request to the android-components project.

## Testing Environment

- **Device:** Samsung SM-G998B
- **OS Version:** 33
- **RAM:** 12 GB
- **GPU:** Mali-G78 MP14
- **CPU:** Octa-core (1x2.9 GHz Cortex-X1 & 3x2.80 GHz Cortex-A78 & 4x2.2 GHz Cortex-A55)
- **Iterations:** 5 tests were conducted for each performance metric.

