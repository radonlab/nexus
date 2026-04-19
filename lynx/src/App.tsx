import { List } from '@lynx-js/lynx-ui';
import './App.css';

type ChatRole = 'user' | 'assistant';

type ChatMessage = {
  id: string;
  role: ChatRole;
  content: string;
};

const MOCK_MESSAGES: ChatMessage[] = [
  {
    id: '1',
    role: 'assistant',
    content: '你好，我是演示助手。下面是一条静态 mock 对话。',
  },
  {
    id: '2',
    role: 'user',
    content: '帮我用一句话解释什么是 Lynx。',
  },
  {
    id: '3',
    role: 'assistant',
    content:
      'Lynx 是一套跨端渲染方案：用类 Web 的开发方式（ReactLynx + CSS）把界面映射到原生视图，而不是传统 WebView。',
  },
  {
    id: '4',
    role: 'user',
    content: '谢谢，界面是 mock 数据，没有接真实 API。',
  },
  {
    id: '5',
    role: 'assistant',
    content: '没问题。把 MOCK_MESSAGES 换成接口返回或状态即可扩展为真实聊天。',
  },
];

export function App() {
  return (
    <view className="app">
      <List
        listId="chat-list"
        listType="single"
        spanCount={1}
        scrollOrientation="vertical"
        style={{ width: '100%', height: '100vh' }}
      >
        {MOCK_MESSAGES.map((message) => (
          <list-item
            key={message.id}
            item-key={message.id}
            className={`message ${message.role === 'user' ? 'user' : 'assistant'}`}
          >
            <view className="avatar">
              <text>{message.role === 'user' ? '👤' : '🤖'}</text>
            </view>
            <view className="content">
              <text>{message.content}</text>
            </view>
          </list-item>
        ))}
      </List>
    </view>
  );
}
