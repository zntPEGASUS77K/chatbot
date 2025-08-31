import React, { useState } from 'react';

function MessageInput({ onSend }) {
    const [text, setText] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        if (text.trim()) {
            onSend(text.trim());
            setText('');
        }
    };

    return (
        <form onSubmit={handleSubmit} className="p-4 border-t flex">
            <input type="text" className="flex-1 border rounded-lg px-4 py-2 focus:outline-none focus:ring" placeholder="Tape ton message..." value={text} onChange={(e) => setText(e.target.value)}/>
            <button type="submit" className="ml-2 bg-indigo-600 text-white rounded-lg px-4 py-2 hover:bg-indigo-700">
                Envoyer
            </button>
        </form>
    );
}

export default MessageInput;
