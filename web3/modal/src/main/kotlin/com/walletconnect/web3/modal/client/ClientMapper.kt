package com.walletconnect.web3.modal.client

import com.walletconnect.sign.client.Sign

// toModal()

internal fun Sign.Model.ApprovedSession.toModal() = Modal.Model.ApprovedSession(topic, metaData, namespaces.toModal(), accounts)

internal fun Map<String, Sign.Model.Namespace.Session>.toModal() = mapValues { (_, namespace) -> Modal.Model.Namespace.Session(namespace.chains, namespace.accounts, namespace.methods, namespace.events)  }

internal fun Sign.Model.RejectedSession.toModal() = Modal.Model.RejectedSession(topic, reason)

internal fun Sign.Model.UpdatedSession.toModal() = Modal.Model.UpdatedSession(topic, namespaces.toModal())

internal fun Sign.Model.SessionEvent.toModal() = Modal.Model.SessionEvent(name, data)

internal fun Sign.Model.Session.toModal() = Modal.Model.Session(pairingTopic, topic, expiry, namespaces.toModal(), metaData)

internal fun Sign.Model.DeletedSession.toModal() = when(this) {
    is Sign.Model.DeletedSession.Error -> Modal.Model.DeletedSession.Error(error)
    is Sign.Model.DeletedSession.Success -> Modal.Model.DeletedSession.Success(topic, reason)
}

internal fun Sign.Model.SessionRequestResponse.toModal() = Modal.Model.SessionRequestResponse(topic, chainId, method, result.toModal())

internal fun Sign.Model.JsonRpcResponse.toModal() = when(this) {
    is Sign.Model.JsonRpcResponse.JsonRpcError -> Modal.Model.JsonRpcResponse.JsonRpcError(id, code, message)
    is Sign.Model.JsonRpcResponse.JsonRpcResult -> Modal.Model.JsonRpcResponse.JsonRpcResult(id, result)
}

internal fun Sign.Model.ConnectionState.toModal() = Modal.Model.ConnectionState(isAvailable)

internal fun Sign.Model.Error.toModal() = Modal.Model.Error(throwable)

// toSign()
internal fun Modal.Params.Connect.toSign() = Sign.Params.Connect(namespaces?.toSign(), optionalNamespaces?.toSign(), properties, pairing)

internal fun Map<String, Modal.Model.Namespace.Proposal>.toSign() = mapValues { (_, namespace) -> Sign.Model.Namespace.Proposal(namespace.chains, namespace.methods, namespace.events) }
