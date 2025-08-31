CREATE TABLE chat_sessions (
                               id BIGSERIAL PRIMARY KEY,
                               session_id VARCHAR(255) NOT NULL UNIQUE,
                               created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE chat_messages (
                               id BIGSERIAL PRIMARY KEY,
                               session_id BIGINT NOT NULL REFERENCES chat_sessions(id) ON DELETE CASCADE,
                               content TEXT NOT NULL,
                               type VARCHAR(20) NOT NULL CHECK (type IN ('USER', 'ASSISTANT')),
                               timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_chat_messages_session_id ON chat_messages(session_id);
CREATE INDEX idx_chat_messages_timestamp ON chat_messages(timestamp);
CREATE INDEX idx_chat_sessions_session_id ON chat_sessions(session_id);