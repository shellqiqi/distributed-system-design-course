# 分布式系统设计课程作业

## 控制节点(C节点)

- 产生所有原始事件序列，并生成相应的操作消息，包括资源转移、发起快照和运行结束三类，发送给对应的P节点(i,j,k)
- 根据原始事件，生成快照标准答案
- 接受来自P节点的快照结果

### 输入

对端IP、资源转移次数、快照次数和随机数种子

### 输出

所有快照的标准答案和接收结果

### 其他要求

- 原始事件间隔：-5ln(random)
- 收到所有快照结果后发送运行结束消息