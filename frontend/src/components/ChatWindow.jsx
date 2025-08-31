import React, { useState, useEffect, useRef } from 'react';
import MessageList from './MessageList';
import MessageInput from './MessageInput';
import axios from 'axios';

const API_URL = process.env.REACT_APP_API_URL || 'http://localhost:8000/api';

function ChatWindow({ sessionId }) {
    const [messages, setMessages] = useState([]);
    const [loading, setLoading] = useState(true);
    const listRef = useRef();

    useEffect(() => {
        async function fetchMessages() {
            const { data } = await axios.get(`${API_URL}/session/${sessionId}/messages`);
            setMessages(data);
            setLoading(false);
        }
        fetchMessages();
    }, [sessionId]);

    const sendMessage = async (text) => {
        const { data } = await axios.post(`${API_URL}/chat`, {
            sessionId,
            message: text,
        });
        setMessages((prev) => [...prev, { content: text, type: 'USER', timestamp: new Date().toISOString() }]);
        setMessages((prev) => [...prev, { content: data.response, type: 'ASSISTANT', timestamp: new Date().toISOString() }]);
    };

    useEffect(() => {
        listRef.current?.scrollTo({ top: listRef.current.scrollHeight, behavior: 'smooth' });
    }, [messages]);

    return (
        <div className="flex flex-col h-full">
            <div className="bg-indigo-600 text-white text-lg font-bold p-4">Chatbot</div>
            <div className="flex-1 overflow-auto p-4" ref={listRef}>
                {loading ? <p>Chargement…</p> : <MessageList messages={messages} />}
            </div>
            <MessageInput onSend={sendMessage} />
        </div>
    );
}

export default ChatWindow;
