"""add gpt4v supports

Revision ID: 8fe468ba0ca5
Revises: a9836e3baeee
Create Date: 2023-11-09 11:39:00.006432

"""
import sqlalchemy as sa
from alembic import op
from sqlalchemy.dialects import postgresql

# revision identifiers, used by Alembic.
revision = '8fe468ba0ca5'
down_revision = 'a9836e3baeee'
branch_labels = None
depends_on = None


def upgrade():
    # ### commands auto generated by Alembic - please adjust! ###
    op.create_table('message_files',
    sa.Column('id', postgresql.UUID(), server_default=sa.text('uuid_generate_v4()'), nullable=False),
    sa.Column('message_id', postgresql.UUID(), nullable=False),
    sa.Column('type', sa.String(length=255), nullable=False),
    sa.Column('transfer_method', sa.String(length=255), nullable=False),
    sa.Column('url', sa.Text(), nullable=True),
    sa.Column('upload_file_id', postgresql.UUID(), nullable=True),
    sa.Column('created_by_role', sa.String(length=255), nullable=False),
    sa.Column('created_by', postgresql.UUID(), nullable=False),
    sa.Column('created_at', sa.DateTime(), server_default=sa.text('CURRENT_TIMESTAMP(0)'), nullable=False),
    sa.PrimaryKeyConstraint('id', name='message_file_pkey')
    )
    with op.batch_alter_table('message_files', schema=None) as batch_op:
        batch_op.create_index('message_file_created_by_idx', ['created_by'], unique=False)
        batch_op.create_index('message_file_message_idx', ['message_id'], unique=False)

    with op.batch_alter_table('app_model_configs', schema=None) as batch_op:
        batch_op.add_column(sa.Column('file_upload', sa.Text(), nullable=True))

    with op.batch_alter_table('upload_files', schema=None) as batch_op:
        batch_op.add_column(sa.Column('created_by_role', sa.String(length=255), server_default=sa.text("'account'::character varying"), nullable=False))

    # ### end Alembic commands ###


def downgrade():
    # ### commands auto generated by Alembic - please adjust! ###
    with op.batch_alter_table('upload_files', schema=None) as batch_op:
        batch_op.drop_column('created_by_role')

    with op.batch_alter_table('app_model_configs', schema=None) as batch_op:
        batch_op.drop_column('file_upload')

    with op.batch_alter_table('message_files', schema=None) as batch_op:
        batch_op.drop_index('message_file_message_idx')
        batch_op.drop_index('message_file_created_by_idx')

    op.drop_table('message_files')
    # ### end Alembic commands ###
