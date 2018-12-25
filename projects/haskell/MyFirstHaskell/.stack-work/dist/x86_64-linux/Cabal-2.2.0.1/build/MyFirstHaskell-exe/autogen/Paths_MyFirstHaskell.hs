{-# LANGUAGE CPP #-}
{-# LANGUAGE NoRebindableSyntax #-}
{-# OPTIONS_GHC -fno-warn-missing-import-lists #-}
module Paths_MyFirstHaskell (
    version,
    getBinDir, getLibDir, getDynLibDir, getDataDir, getLibexecDir,
    getDataFileName, getSysconfDir
  ) where

import qualified Control.Exception as Exception
import Data.Version (Version(..))
import System.Environment (getEnv)
import Prelude

#if defined(VERSION_base)

#if MIN_VERSION_base(4,0,0)
catchIO :: IO a -> (Exception.IOException -> IO a) -> IO a
#else
catchIO :: IO a -> (Exception.Exception -> IO a) -> IO a
#endif

#else
catchIO :: IO a -> (Exception.IOException -> IO a) -> IO a
#endif
catchIO = Exception.catch

version :: Version
version = Version [0,1,0,0] []
bindir, libdir, dynlibdir, datadir, libexecdir, sysconfdir :: FilePath

bindir     = "/home/kelly/projects/haskell/MyFirstHaskell/.stack-work/install/x86_64-linux/lts-12.13/8.4.3/bin"
libdir     = "/home/kelly/projects/haskell/MyFirstHaskell/.stack-work/install/x86_64-linux/lts-12.13/8.4.3/lib/x86_64-linux-ghc-8.4.3/MyFirstHaskell-0.1.0.0-E9TB63ycJd2JlKaDFMFqSw-MyFirstHaskell-exe"
dynlibdir  = "/home/kelly/projects/haskell/MyFirstHaskell/.stack-work/install/x86_64-linux/lts-12.13/8.4.3/lib/x86_64-linux-ghc-8.4.3"
datadir    = "/home/kelly/projects/haskell/MyFirstHaskell/.stack-work/install/x86_64-linux/lts-12.13/8.4.3/share/x86_64-linux-ghc-8.4.3/MyFirstHaskell-0.1.0.0"
libexecdir = "/home/kelly/projects/haskell/MyFirstHaskell/.stack-work/install/x86_64-linux/lts-12.13/8.4.3/libexec/x86_64-linux-ghc-8.4.3/MyFirstHaskell-0.1.0.0"
sysconfdir = "/home/kelly/projects/haskell/MyFirstHaskell/.stack-work/install/x86_64-linux/lts-12.13/8.4.3/etc"

getBinDir, getLibDir, getDynLibDir, getDataDir, getLibexecDir, getSysconfDir :: IO FilePath
getBinDir = catchIO (getEnv "MyFirstHaskell_bindir") (\_ -> return bindir)
getLibDir = catchIO (getEnv "MyFirstHaskell_libdir") (\_ -> return libdir)
getDynLibDir = catchIO (getEnv "MyFirstHaskell_dynlibdir") (\_ -> return dynlibdir)
getDataDir = catchIO (getEnv "MyFirstHaskell_datadir") (\_ -> return datadir)
getLibexecDir = catchIO (getEnv "MyFirstHaskell_libexecdir") (\_ -> return libexecdir)
getSysconfDir = catchIO (getEnv "MyFirstHaskell_sysconfdir") (\_ -> return sysconfdir)

getDataFileName :: FilePath -> IO FilePath
getDataFileName name = do
  dir <- getDataDir
  return (dir ++ "/" ++ name)
