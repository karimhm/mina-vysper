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
package org.apache.vysper.xmpp.modules.extension.xep0060_pubsub.handler;

import org.apache.vysper.xmpp.modules.core.base.handler.IQHandler;
import org.apache.vysper.xmpp.modules.extension.xep0060_pubsub.AbstractPublishSubscribeTestCase;
import org.apache.vysper.xmpp.modules.extension.xep0060_pubsub.model.LeafNode;
import org.apache.vysper.xmpp.protocol.NamespaceURIs;
import org.apache.vysper.xmpp.protocol.ResponseStanzaContainer;
import org.apache.vysper.xmpp.stanza.IQStanza;
import org.apache.vysper.xmpp.stanza.IQStanzaType;
import org.apache.vysper.xmpp.stanza.StanzaBuilder;

/**
 * @author The Apache MINA Project (http://mina.apache.org)
 */
public class PubSubUnsubscribeTestCase extends AbstractPublishSubscribeTestCase {

	@Override
	protected StanzaBuilder buildInnerElement(StanzaBuilder sb) {
		sb.startInnerElement("unsubscribe");
		sb.addAttribute("node", pubsub.getResource());
		sb.addAttribute("jid", client.getFullQualifiedName());
		sb.endInnerElement();
		return sb;
	}

	@Override
	protected IQHandler getHandler() {
		return new PubSubUnsubscribeHandler(root);
	}

	@Override
	protected String getNamespace() {
		return NamespaceURIs.XEP0060_PUBSUB;
	}

	@Override
	protected IQStanzaType getStanzaType() {
		return IQStanzaType.SET;
	}
	
	public void testUnsubscribe() throws Exception {
		// subscribe the client to the default node
		node.subscribe("somethingarbitrary", client);
		
		// make sure it is subscribed
		assertTrue(node.isSubscribed(client));
		
		// unsubscribe via XMPP
		ResponseStanzaContainer result = sendStanza(getStanza(), true);
		
		// check subscription and response stanza
		assertTrue(result.hasResponse());
		IQStanza response = new IQStanza(result.getResponseStanza());
		assertEquals(IQStanzaType.RESULT.value(),response.getType());
		assertFalse(node.isSubscribed(client));
	}

}
