grammar BaseGrammar;

start: start_expression+; //code

start_expression: (DECLARATION asset)
       | comment;

explicit_code: statement | block_code; //Differs from code since this requires for the text to be an actual logical command.

block_code: comment* BLOCK_START (statement|comment|(DECLARATION assetFunction)|block_code)* BLOCK_END comment*;

statement: if_statement
         | expression;

if_statement: (IF OBRACKETS comparison CBRACKETS comment* explicit_code (ELSE explicit_code)?)+;

expression: (assignment | function_call | functionArgumentDeclaration) END_STATEMENT;

assignment: variable ASSIGN (math_value|math_operation);

math_operation: factor (MATH_OPERATOR_PLUS_MINUS factor)*;

factor: term (MATH_OPERATOR_MULT_DIV term)*;

term: (OBRACKETS math_operation CBRACKETS) | math_value;

comparison: (variable|value|function_call) OPERATOR (variable|value|function_call);

function_call: VARIABLE OBRACKETS function_parameter* CBRACKETS;

function_parameter: (variable|value)','
                    | (variable|value);

functionArgumentDeclaration: FUNCTION_ARGUMENT VARIABLE;

math_value: variable|value|function_call;

variable: VARIABLE | GAME_VARIABLE;

value: STRING
     | REAL;

asset: assetModule | assetSprite | assetItem | assetFunction; //An asset can be a module, function, sprite or item.
//Each have their own rules implemented in the visitor.

assetModule: MODULE comment* VARIABLE
             block_code;

assetSprite: SPRITE comment* VARIABLE comment*
             (BLOCK_START)
             ((assignment END_STATEMENT)|comment)*
             (BLOCK_END|comment*);
             
assetItem: ITEM comment* VARIABLE
            block_code;

assetFunction: FUNCTION comment* VARIABLE block_code;

comment: COMMENT;

//In lexer rules, order matters: the ones that appear first have priority.

IF: 'if';

ELSE: 'else';

DECLARATION: 'define'; //Definition of assets start with this token.

MODULE: 'module'; //Modules must have this token

FUNCTION: 'function'; //Functions must have this token

SPRITE: 'sprite'; //Sprites must have this token

ITEM: 'item'; //Items must have this token

FUNCTION_ARGUMENT: 'functionInput'|'functionOutput';

ASSIGN: '=';

OPERATOR: '=='|'>'|'<'|'>='|'<='|'!=';

BLOCK_START: '{'; //Starts block of code.

BLOCK_END: '}'; //Ends block of code

OBRACKETS: '(';

CBRACKETS: ')';

END_STATEMENT: ';';

MATH_OPERATOR_MULT_DIV: [/*];

MATH_OPERATOR_PLUS_MINUS: [+-];

VARIABLE: ID;

STRING: '"'~["]*'"';

REAL: NUMBER+('.'NUMBER+)?;

fragment ID: (LETTER|'_')(LETTER|NUMBER|'_')*;

fragment LETTER: [a-zA-Z];

fragment NUMBER: [0-9];
                 
GAME_VARIABLE: '['ID']'; //Game variables.

COMMENT: '#'~[\n\r]+;

WHITESPACE: ([ \n\t\r]+) -> skip; //Ignore white space or single-lined comments.
