/*
 * Copyright (C) 2015-2022 Lightbend Inc. <https://www.lightbend.com>
 */

package org.apache.pekko.persistence.query.journal.leveldb

import java.io.File

import org.apache.commons.io.FileUtils

import org.apache.pekko.testkit.AkkaSpec

trait Cleanup { this: AkkaSpec =>
  val storageLocations =
    List(
      "akka.persistence.journal.leveldb.dir",
      "akka.persistence.journal.leveldb-shared.store.dir",
      "akka.persistence.snapshot-store.local.dir").map(s => new File(system.settings.config.getString(s)))

  override protected def atStartup(): Unit = {
    storageLocations.foreach(FileUtils.deleteDirectory)
  }

  override protected def afterTermination(): Unit = {
    storageLocations.foreach(FileUtils.deleteDirectory)
  }
}