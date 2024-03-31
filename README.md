# Mozilla Firefox: Optimized Fork

## Introduction

This repository is a fork of mozilla-firefox/reference-browser focused on performance optimizations.
Below is a summary of the optimizations made, categorized by their impact on startup times and
resource usage.

## Optimization Impact Summary

### Startup Time Optimization

*Hot Startup: Time taken when the application is launched frequently.*
*Cold Startup: Time taken when the application is launched for the first time or after a long idle
period.*
*Warm Startup: Time taken when the application is launched after being recently used.*

### Cold Startup

| Metric | Before Optimization (ms) | After Optimization (ms) | Reduced       |
|--------|--------------------------|-------------------------|---------------|
| p50    | 450.3                    | x                       | Reduced by x% |
| p90    | 470.2                    | x                       | Reduced by x% |
| p99    | 518.8                    | x                       | Reduced by x% |
| ...    | ...                      | ...                     | ...           |

### Resource Usage Optimization

| Metric      | RAM Usage Before | RAM Usage After | CPU Usage Before | CPU Usage After |
|-------------|------------------|-----------------|------------------|-----------------|
| 0 Tab Used  | ~188.9 MB        | Y MB            | -                | -               |
| 1 Tab Used  | ~256.4 MB        | B MB            | -                | -               |
| 5 Tab Used  | ~311 MB          | ...             | ...              | ...             |
| 10 Tab Used | ~383.4 MB        | ...             | ...              | ...             |

*Note: 'Before' and 'After' columns represent the metrics before and after the optimizations were
applied.*

## Detailed Optimizations

Lorem Ipsum

## Performance Data

### Methodology

Lorem Ipsum

### Results

Lorem Ipsum

## Conclusion

Lorem Ipsum

## Testing Environment

- **Device:** Samsung SM-G998B
- **OS Version:** 33
- **RAM:** 12 GB
- **GPU:** Mali-G78 MP14
- **CPU:** Octa-core (1x2.9 GHz Cortex-X1 & 3x2.80 GHz Cortex-A78 & 4x2.2 GHz Cortex-A55)
- **Iterations:** 5 tests were conducted for each performance metric.

