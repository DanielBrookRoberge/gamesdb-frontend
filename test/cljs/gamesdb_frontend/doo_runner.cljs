(ns gamesdb-frontend.doo-runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [gamesdb-frontend.core-test]))

(doo-tests 'gamesdb-frontend.core-test)
