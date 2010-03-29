/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */
package org.apache.vysper.xmpp.modules.roster;

import org.apache.vysper.xmpp.addressing.Entity;

import java.util.Iterator;

/**
 * the whole roster for a user. contains all the user's contacts, with both pending and established subscriptions.
 * the roster is persisted on the server and requested by the client with every login.
 *
 * @author The Apache MINA Project (dev@mina.apache.org)
 */
public interface Roster extends Iterable<RosterItem> {

    public Iterator<RosterItem> iterator();

    public RosterItem getEntry(Entity contact);
}
