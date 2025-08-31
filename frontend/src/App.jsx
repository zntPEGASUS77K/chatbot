import React, { useState, useEffect } from 'react';
import ChatWindow from './components/ChatWindow';

function App() {
    const [sessionId] = useState(() => {
        const sid = localStorage.getItem('sessionId') || crypto.randomUUID();
        localStorage.setItem('sessionId', sid);
        return sid;
    });

    return (
        <div className="h-screen flex items-center justify-center bg-gray-100">
            <div className="w-full max-w-xl h-full max-h-[700px] bg-white shadow-lg rounded-lg overflow-hidden">
                <ChatWindow sessionId={sessionId} />
            </div>
        </div>
    );
}

export default App;
