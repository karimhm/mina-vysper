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
package org.apache.vysper.xmpp.modules.extension.xep0060_pubsub.model;

import java.util.HashMap;
import java.util.Map;

import org.apache.vysper.xmpp.addressing.Entity;

/**
 * @author The Apache MINA Project (http://mina.apache.org)
 *
 */
public class CollectionNode {
	
	protected Map<Entity, LeafNode> storage;
	
	public CollectionNode() {
		storage = new HashMap<Entity, LeafNode>();
	}
	
	public LeafNode find(Entity jid) {
		return storage.get(jid);
	}
	
	public LeafNode createNode(Entity jid) throws DuplicateNodeException {
		if(storage.containsKey(jid)) {
			throw new DuplicateNodeException(jid.getFullQualifiedName() + " already present");
		}
		
		LeafNode node = new LeafNode(jid);
		storage.put(jid, node);
		
		return node;
	}
}
