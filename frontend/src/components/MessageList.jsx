import React from 'react';

function MessageList({ messages }) {
    return messages.map((msg, idx) => {
        const align = msg.type === 'USER' ? 'justify-end' : 'justify-start';
        const bg = msg.type === 'USER' ? 'bg-blue-500 text-white' : 'bg-gray-200 text-gray-800';

        return (
            <div key={idx} className={`flex ${align} mb-2`}>
                <div className={`px-4 py-2 rounded-lg max-w-[75%] ${bg}`}>
                    <div className="whitespace-pre-wrap">{msg.content}</div>
                    <div className="text-xs text-gray-500 mt-1 text-right">
                        {new Date(msg.timestamp).toLocaleTimeString()}
                    </div>
                </div>
            </div>
        );
    });
}

export default MessageList;
